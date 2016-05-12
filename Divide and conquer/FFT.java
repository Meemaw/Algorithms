import java.util.Scanner;

/**
 * Created by Meemaw on 23/04/16.
 */
public class FFT {

    static class Complex {
        double re;
        double im;

        public Complex(double real, double imag) {
            re = real;
            im = imag;
        }

        public String toString() {
            double tRe = (double) Math.round(re * 100000) / 100000;
            double tIm = (double) Math.round(im * 100000) / 100000;
            if (tIm == 0) return tRe + "";
            if (tRe == 0) return tIm + "i";
            if (tIm < 0) return tRe + "-" + (-tIm) + "i";
            return tRe + "+" + tIm + "i";
        }

        public Complex plus(Complex b) {
            Complex a = this;
            double real = a.re + b.re;
            double imag = a.im + b.im;
            return new Complex(real, imag);
        }

        public Complex minus(Complex b) {
            Complex a = this;
            double real = a.re - b.re;
            double imag = a.im - b.im;
            return new Complex(real, imag);
        }

        public Complex times(Complex b) {
            Complex a = this;
            double real = a.re * b.re - a.im * b.im;
            double imag = a.re * b.im + a.im * b.re;
            return new Complex(real, imag);
        }

        public Complex times(double alpha) {
            return new Complex(alpha * re, alpha * im);
        }

        public Complex reciprocal() {
            double scale = re * re + im * im;
            return new Complex(re / scale, -im / scale);
        }

        public Complex divides(Complex b) {
            Complex a = this;
            return a.times(b.reciprocal());
        }

        public Complex exp() {
            return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
        }

        public Complex pow(int k) {

            Complex c = new Complex(1, 0);
            for (int i = 0; i < k; i++) {
                c = c.times(this);
            }
            return c;
        }

    }


    private static Complex[] recursiveFFT(Complex[] pol) {
        int len = pol.length;
        if (len == 1) return pol;
        Complex[] evenTerms = new Complex[len / 2];

        for (int i = 0; i < len / 2; i++) {
            evenTerms[i] = pol[2 * i];
        }

        Complex[] ys = recursiveFFT(evenTerms);

        Complex[] oddTerms = new Complex[len / 2];
        for (int i = 0; i < len / 2; i++) {
            oddTerms[i] = pol[2 * i + 1];
        }

        Complex[] yl = recursiveFFT(oddTerms);
        
        double curr = 2 * Math.PI / len;
        Complex w = new Complex(Math.cos(curr), Math.sin(curr));
        Complex wk = new Complex(1, 0);
        Complex[] combine = new Complex[len];
        for (int i = 0; i < len / 2; i++) {
            combine[i] = ys[i].plus(wk.times(yl[i]));
            combine[i + len / 2] = ys[i].minus(wk.times(yl[i]));
            wk = wk.times(w);

        }

        printTrace(combine);
        return combine;
    }

    private static void printTrace(Complex[] x) {
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i] + " ");
        }
        System.out.println();
    }

    private static Complex[] fillPolynom(Complex[] x) {
        int prva = 2;
        int dolzina = x.length;
        while (prva < dolzina) {
            prva *= 2;
        }
        if (prva == dolzina) return x;

        Complex[] novi = new Complex[prva];
        int i = 0;
        for (; i < dolzina; i++) {
            novi[i] = x[i];
        }
        for (; i < prva; i++) {
            novi[i] = new Complex(0, 0);
        }
        return novi;

    }

    public static void main(String[] args) {
        int dolzina = Integer.parseInt(args[0]);
        Scanner sc = new Scanner(System.in);


        Complex[] poly = new Complex[dolzina];


        for (int i = 0; i < dolzina; i++) {
            poly[i] = new Complex(sc.nextDouble(), 0);
        }
        poly = fillPolynom(poly);


        Complex[] fft = recursiveFFT(poly);

    }
}
