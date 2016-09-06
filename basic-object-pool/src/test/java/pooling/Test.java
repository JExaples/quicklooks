package pooling;

import java.util.Hashtable;

public class Test {

	public static void main(String[] args) {
		Hashtable<String, String> hash = new Hashtable<String, String>();
		hash.put("harambe", "dead");
		
		System.out.println(hash);
		hash.remove("bantu");
		System.out.println(hash);
	}

}
