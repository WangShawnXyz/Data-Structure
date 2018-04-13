package Map;

public class DefaultEqualityTester implements EqualityTester {
    public DefaultEqualityTester(){}
    @Override
    public boolean isEqualTo(Object a, Object b) {
        return a.equals(b);
    }
}
