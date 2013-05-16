
public class Complex {
	private double real, imag;
	
	public Complex () {
		this.real = 0;
		this.imag = 0;
	}
	
	public Complex (double r, double i) {
		this.real = r;
		this.imag = i;
	}
	
	public synchronized double getReal() {
		return this.real;
	}
	
	public synchronized double getImag() {
		return this.imag;
	}
	
	public synchronized void setReal (double r) {
		this.real = r;
	}
	
	public synchronized void setImag (double i) {
		this.imag = i;
	}
	
	public synchronized double getNorm () {
		return Math.sqrt((this.real * this.real) + (this.imag * this.imag));
	}
	
	public double norm (Complex z) {
		return Math.sqrt((z.getReal() * z.getReal()) + (z.getImag() * z.getImag()));	
	}
	
	public synchronized Complex add (Complex op) {
		Complex result = new Complex();
		result.setReal(this.real + op.getReal());
		result.setImag(this.imag + op.getImag());
		return result;
	}
	
	public synchronized Complex mult (Complex op) {
		Complex result = new Complex();
		result.setReal(this.real * op.getReal() - this.imag * op.getImag());
		result.setImag(this.real * op.getImag() + this.imag * op.getReal());
		return result;
	}
	
}