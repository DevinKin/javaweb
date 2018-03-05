package cn.itcast.uuid;

import cn.itcast.utils.CommonUtils;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UUIDTest {
    @Test
    public void func1() {
        UUID uuid = UUID.randomUUID();
        String string = uuid.toString();
        string = string.replace("-","");
        string = string.toUpperCase();
        System.out.println(string);
        System.out.println(CommonUtils.uuid());
    }
}
