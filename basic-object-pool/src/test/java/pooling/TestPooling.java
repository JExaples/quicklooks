package pooling;

import java.util.Date;
import java.util.UUID;

import com.sarvika.pooling.ObjectPool;

public class TestPooling {
	
	public static ModelClassPool pool = new ModelClassPool();

	public static void main(String[] args) {
		InsertThread insertThread = new InsertThread();
		CheckoutThread checkoutThread = new CheckoutThread();
		
		insertThread.start();
		checkoutThread.start();
	}

}

class ModelClass {

	private String id;
	private Date createTime;

	public ModelClass() {
		createTime = new Date();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public String toString() {
		return "ModelClass [id=" + id + ", createTime=" + createTime + "]";
	}

}

class ModelClassPool extends ObjectPool<ModelClass> {

	@Override
	protected ModelClass create() {
		ModelClass m = new ModelClass();
		m.setId(UUID.randomUUID().toString());
		return m;
	}

	@Override
	public boolean validate(ModelClass o) {
		return o.getId() != null;
	}

	@Override
	public void expire(ModelClass o) {
		o.setId(null);
	}
	
//	@Override
//	public synchronized void checkIn(ModelClass t) {
//		super.checkIn(t);
//		System.out.println("Locked objects: " + getLocked());
//		System.out.println("Unlocked Objects: " + getUnlocked());
//	}
//	
//	@Override
//	public synchronized ModelClass checkOut() {
//		ModelClass m = super.checkOut();
//
//		System.out.println("Locked objects: " + getLocked());
//		System.out.println("Unlocked Objects: " + getUnlocked());
//		
//		return m;
//	}

}

class InsertThread extends Thread {
	@Override
	public void run() {
		while (true) {
			try {
				ModelClass m = TestPooling.pool.create();
				System.out.println("Checked In: " + m);
				TestPooling.pool.checkIn(m);
				Thread.sleep(3000);
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
}

class CheckoutThread extends Thread {
	@Override
	public void run() {
		while (true) {
			try {
				ModelClass m = TestPooling.pool.checkOut();
				System.out.println("Checked Out: " + m);
				Thread.sleep(7000);
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
}