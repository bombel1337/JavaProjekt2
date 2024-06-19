package wit.projekt.Registry;

import java.util.List;

public abstract class Registry<T> {
    public abstract List<T> getItems();
    public abstract void addItem(T item);
    public abstract T editItem(String id, String... params);
    public abstract void deleteItem(String id);
}
