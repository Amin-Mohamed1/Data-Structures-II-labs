public interface PerfectHashing<T> {
    public int insert(T key);
    public boolean search(T key);
    public boolean delete(T key);
    public int getNumberOfRehashing();
}
