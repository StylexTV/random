package net.marmo.random;

public class Random {
	
	private static final long MULTIPLIER = 0x5DEECE66DL;
	private static final long INCREMENT = 0xB;
	
	private static final int INITIAL_SKIP_AMOUNT = 10;
	
	private long state;
	
	public Random() {
		this(System.nanoTime());
	}
	
	public Random(long seed) {
		setState(seed);
	}
	
	public float nextFloat(float min, float max) {
		return (float) nextDouble(min, max);
	}
	
	public float nextFloat(float range) {
		return (float) nextDouble(range);
	}
	
	public float nextFloat() {
		return (float) nextDouble();
	}
	
	public double nextDouble(double min, double max) {
		double range = max - min;
		
		return min + nextDouble(range);
	}
	
	public double nextDouble(double range) {
		return nextDouble() * range;
	}
	
	public double nextDouble() {
		long n = nextLong();
		
		double d = (double) n / Long.MAX_VALUE;
		
		return (d + 1) / 2;
	}
	
	public boolean nextBoolean() {
		return nextLong(2) == 0;
	}
	
	public byte nextByte() {
		return (byte) nextLong();
	}
	
	public int nextInt(int min, int max) {
		return (int) nextLong(min, max);
	}
	
	public int nextInt(int range) {
		return (int) nextLong(range);
	}
	
	public long nextLong(long min, long max) {
		long range = max - min + 1;
		
		return min + nextLong(range);
	}
	
	public long nextLong(long range) {
		long n = nextLong();
		
		return Math.abs(n) % range;
	}
	
	private long nextLong() {
		return nextState();
	}
	
	private void skipStates(int amount) {
		for(int i = 0; i < amount; i++) skipState();
	}
	
	private void skipState() {
		nextState();
	}
	
	private long nextState() {
		return state = state * MULTIPLIER + INCREMENT;
	}
	
	public long getState() {
		return state;
	}
	
	public void setState(long state) {
		this.state = state;
		
		skipStates(INITIAL_SKIP_AMOUNT);
	}
	
}
