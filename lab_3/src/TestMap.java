public class TestMap {
    public static void main(String[] args) throws SizeError {

        BtreeMap btreeMap = new BtreeMap<Character, Integer>(3);

        btreeMap.addElement(1,2);
        btreeMap.addElement(2,3);
        btreeMap.addElement(3,3);
        btreeMap.addElement(4,3);

        System.out.println(btreeMap);

    }
}
