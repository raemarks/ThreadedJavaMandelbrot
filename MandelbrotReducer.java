import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MandelbrotReducer {

	private final String filename;
	private final int xsize, ysize;
	private final ArrayList<ArrayList<Integer>> red;
	private final ArrayList<ArrayList<Integer>> green;
	private final ArrayList<ArrayList<Integer>> blue;
	
	public MandelbrotReducer(String file, int x, int y, ArrayList<ArrayList<Integer>> rr,
			ArrayList<ArrayList<Integer>> gg, ArrayList<ArrayList<Integer>> bb) {
		
		this.filename = file;
		this.xsize = x;
		this.ysize = y;
		this.red = rr;
		this.blue = bb;
		this.green = gg;
	}

	public void generate() throws IOException {
		
		File file = new File(filename);
		if (!file.exists()) {
			file.createNewFile();
		}
		PrintWriter fileout = new PrintWriter(new FileWriter(file.getAbsoluteFile()));
		
		fileout.println("P3");
		fileout.println(xsize + " " + ysize);
		fileout.println("255");
		
		for (int i = 0; i < red.size(); i++) {
			for (int j = 0; j < red.get(i).size(); j ++) {
				fileout.print(red.get(i).get(j) + " " + green.get(i).get(j) + " " + blue.get(i).get(j) + " ");
			}
		}
		
		fileout.close();
	}
}