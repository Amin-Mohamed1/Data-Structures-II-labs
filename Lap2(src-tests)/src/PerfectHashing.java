public interface PerfectHashing<T> {
    public boolean insert(T s);
    public boolean search(T key);
    public boolean delete(T key);
}
