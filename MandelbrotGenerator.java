import java.util.ArrayList;

public class MandelbrotGenerator implements Runnable {

	private final double PI = 3.14159;
	private final int x1, x2, y1, y2, xsize, ysize;
	private final Complex lowleft, upright;
	private ArrayList<Integer> red;
	private ArrayList<Integer> green;
	private ArrayList<Integer> blue;

	MandelbrotGenerator(int x1, int x2, int y1, int y2, int xsize, int ysize,
			ArrayList<Integer> rr, ArrayList<Integer> gg, ArrayList<Integer> bb) {

		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.xsize = xsize;
		this.ysize = ysize;
		this.lowleft = MandelbrotMapper.getLowleft();
		this.upright = MandelbrotMapper.getUpright();
		this.red = rr;
		this.green = gg;
		this.blue = bb;

	}

	public void run() {
	
		double a, b, vn;
		int iterations;

		
		for (int y = y1; y < y2; y++) {
			for (int x = x1; x < x2; x++) {

				a = lowleft.getReal()
						+ (x * (upright.getReal() - lowleft.getReal()) / (xsize - 1));
				b = upright.getImag()
						- (y * (upright.getImag() - lowleft.getImag()) / (ysize - 1));

				Complex c = new Complex(a, b);

				iterations = Mandelbrot(c, new Complex(), 255, 1);

				vn = (double) iterations / 255;

				red.add((int) (255 * Math.sin(PI * vn)));
				green.add((int) ((255 * Math.pow(Math.sin(PI * vn), 2) + (1 - vn)) / 2));
				blue.add((int) (255 * (1 - vn)));

			}
		}
	}

	int Mandelbrot(Complex c, Complex z, int max, int iterations) {

		while ((iterations <= max) && (z.getNorm() <= 4)) {
			z = c.add(z.mult(z));
			iterations++;
		}
		return iterations;
	}

}