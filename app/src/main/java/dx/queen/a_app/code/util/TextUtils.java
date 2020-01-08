package dx.queen.a_app.code.util;

import androidx.annotation.Nullable;

public class TextUtils {
    public static boolean isEmpty(@Nullable String text) {
        return text == null || text.trim().length() == 0;
    }
}
