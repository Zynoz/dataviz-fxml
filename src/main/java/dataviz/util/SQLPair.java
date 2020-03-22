package dataviz.util;

import lombok.Value;

@Value(staticConstructor = "of")
public class SQLPair<T, S> {
    T type;
    S sql;
}
