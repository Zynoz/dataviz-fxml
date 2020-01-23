package dataviz.util;

import lombok.Value;

@Value(staticConstructor = "of")
public class SQLPair<T, S> {
    private T type;
    private S sql;

}
