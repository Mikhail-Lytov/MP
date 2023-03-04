public class TestMap {
    public static void main(String[] args) throws SizeError {

        BtreeMap btreeMap = new BtreeMap<Character, Integer>(3);

        btreeMap.insert(1,"a");
        btreeMap.insert(2,"b");

        System.out.println(btreeMap);

    }
}
