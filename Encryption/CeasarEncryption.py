__author__ = 'Meemaw'


import string



## Methods for encryption
def buildCoder(shift):
    coder = {}
    for char in string.ascii_lowercase:
        stevilo = ord(char) - 96 + shift
        if stevilo > 26:
            stevilo-=26
        coder[char] = chr(stevilo+96)
    for char in string.ascii_uppercase:
        stevilo = ord(char) - 64 + shift
        if stevilo > 26:
            stevilo-=26
        coder[char] = chr(stevilo+64)
    return coder


def applyCoder(text, coder):
    encrypted = ""
    for char in text:
        value = coder.get(char, char)
        encrypted+=value
    return encrypted

def applyShift(text, shift):
    coder = buildCoder(shift)
    return applyCoder(text,coder)


## Methods for decryption

def loadWords():
    print("Loading word list from file...")
    inFile = open("fileName.txt", 'r')
    wordList = inFile.read().split()
    print("  ", len(wordList), "words loaded.")
    return wordList

def isWord(wordList, word):
    word = word.lower()
    word = word.strip(" !@#$%^&*()-_+={}[]|\\:;'<>?,./\"")
    return word in wordList


def findBestShift(wordList, text):
    best = 0
    bestShift = 0
    for shift in range(1,27):
        current = 0
        coder = buildCoder(shift)
        encrypted = applyCoder(text,coder)
        seznamBesed = encrypted.split()
        print(seznamBesed)
        for word in seznamBesed:
            preglej = "".join(c for c in word if c not in ("!",".",",","?"))
            if isWord(wordList,preglej):
                current += 1
        if current > best:
            best = current
            bestShift = shift
    return shift


def decryptStory(text):
    wordList = loadWords()
    bestShift = findBestShift(wordList,text)
    encrypted = applyShift(text,bestShift)
    return encrypted