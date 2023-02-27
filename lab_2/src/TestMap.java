public class TestMap {
    public static void main(String[] args) throws DataError {
        Data data = new Data<>(2,2);
        Map map = new Map();
        System.out.println(map.checking_emptiness());
        map.add_element(data);
        Data data_1 = new Data<>(3,1);
        map.add_element(data_1);
        Data data_2 = new Data<>(1,3);
        map.add_element(data_2);
        Data data1 = map.search_element(1);
        data1.setItem(10);

        map.print();
    }
}
