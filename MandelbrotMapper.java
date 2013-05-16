import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MandelbrotMapper {
	private volatile static ArrayList<ArrayList<Integer>> redList = new ArrayList<ArrayList<Integer>>();
	private volatile static ArrayList<ArrayList<Integer>> greenList = new ArrayList<ArrayList<Integer>>();
	private volatile static ArrayList<ArrayList<Integer>> blueList = new ArrayList<ArrayList<Integer>>();

	private static int xsize, ysize;
	private static Complex lowleft = new Complex(), upright = new Complex();
	private static String filename;

	private static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException, IOException {

		setCorners();
		setSize();
		setFilename();
		
		
	
					
		for (int i = 0; i < 5; i++) {

			redList.add(new ArrayList<Integer>());
			greenList.add(new ArrayList<Integer>());
			blueList.add(new ArrayList<Integer>());
		}
		
		
		//for each of these, pass in a horizontal strip of the image to calculate. 
		Thread Thread1 = new Thread(new MandelbrotGenerator(0, xsize, 0, ysize / 5, xsize, ysize, redList.get(0), greenList.get(0), blueList.get(0)));
		Thread1.start();
		
		Thread Thread2 = new Thread(new MandelbrotGenerator(0, xsize, ysize / 5, 2*ysize / 5, xsize, ysize, redList.get(1), greenList.get(1), blueList.get(1)));
		Thread2.start();
		
		Thread Thread3 = new Thread(new MandelbrotGenerator(0, xsize, 2*ysize / 5, 3*ysize / 5, xsize, ysize, redList.get(2), greenList.get(2), blueList.get(2)));
		Thread3.start();
		
		Thread Thread4 = new Thread(new MandelbrotGenerator(0, xsize, 3*ysize / 5, 4*ysize / 5, xsize, ysize, redList.get(3), greenList.get(3), blueList.get(3)));
		Thread4.start();
		
		Thread Thread5 = new Thread(new MandelbrotGenerator(0, xsize, 4*ysize / 5, ysize, xsize, ysize, redList.get(4), greenList.get(4), blueList.get(4)));
		Thread5.start();
		
		
		Thread1.join();
		Thread2.join();
		Thread3.join();
		Thread4.join();
		Thread5.join();
		
		
		
		new MandelbrotReducer (filename, xsize, ysize, redList, greenList, blueList).generate();
		System.out.println("Done!");
	}

	private static void setCorners() {
		System.out
				.println("Enter the complex number corresponding to the lower left corner of the image, first the real part followed by the imaginary part, separated by a space: ");
		lowleft.setReal(in.nextDouble());
		lowleft.setImag(in.nextDouble());

		System.out
				.println("Enter the complex number corresponding to the upper right corner of the image, first the real part followed by the imaginary part, separated by a space: ");
		upright.setReal(in.nextDouble());
		upright.setImag(in.nextDouble());
	}

	private static void setSize() {

		System.out.println("Enter the amount of horizontal pixels: ");
		xsize = in.nextInt();
	
		ysize = (int) (((double) xsize * (upright.getImag() - lowleft.getImag())) / (upright
				.getReal() - lowleft.getReal()));
		System.out.println("Number of vertical pixels: " + ysize);
	}

	private static void setFilename() {
		System.out
				.println("Enter the filename for the generated Mandelbrot image, with a .ppm extension: ");
		filename = in.next();
	}

	public static Complex getLowleft() {
		return lowleft;
	}

	public static Complex getUpright() {
		return upright;
	}

}