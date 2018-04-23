package cn.devinkin.demo1;

import com.thoughtworks.xstream.XStream;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示XStream
 */
public class Demo1 {
    // 返回javabean集合
    public List<Province> getProvinceList() {
        Province p1 = new Province();
        p1.setName("北京");
        p1.addCity(new City("东城区", "DongChengQu"));
        p1.addCity(new City("昌平区", "ChangPingQu"));

        Province p2 = new Province();
        p2.setName("辽宁");
        p2.addCity(new City("沈阳", "ShenYang"));
        p2.addCity(new City("葫芦岛", "HuLuDao"));

        List<Province> provinceList = new ArrayList<Province>();
        provinceList.add(p1);
        provinceList.add(p2);

        return provinceList;
    }

    /**
     * <list>
        <cn.devinkin.demo1.Province>
            <name>北京</name>
            <cities>
                <cn.devinkin.demo1.City>
                    <name>东城区</name>
                    <description>DongChengQu</description>
                </cn.devinkin.demo1.City>
                <cn.devinkin.demo1.City>
                    <name>昌平区</name>
                    <description>ChangPingQu</description>
                </cn.devinkin.demo1.City>
            </cities>
        </cn.devinkin.demo1.Province>
        <cn.devinkin.demo1.Province>
            <name>辽宁</name>
            <cities>
                <cn.devinkin.demo1.City>
                    <name>沈阳</name>
                    <description>ShenYang</description>
                </cn.devinkin.demo1.City>
                <cn.devinkin.demo1.City>
                    <name>葫芦岛</name>
                    <description>HuLuDao</description>
                </cn.devinkin.demo1.City>
            </cities>
        </cn.devinkin.demo1.Province>
     </list>
     */

    @Test
    public void func1() {
        List<Province> proList = getProvinceList();
        /**
         * 1. 创建XStream对象
         * 2. 调用toXML把集合转换成xml字符串
         */
        XStream xStream = new XStream();
        String s = xStream.toXML(proList);
        System.out.println(s);
    }


    /**
     * 别名(alias)
     * 希望：
     *  1. 默认List类型对应<list>元素，希望让List类型对应<china>元素
     *  2. 默认Province类型对应<cn.itcast.demo1.Province>，希望让它对应<province>
     *  3. 默认City类型对应<cn.itcast.demo1.City>，希望让它对应<city>元素
     */
    @Test
    public void func2() {
        List<Province> proList = getProvinceList();

        XStream xStream = new XStream();

        /**
         * 给指定的类型指定别名
         */
        //给List类型指定别名为china
        xStream.alias("china", List.class);
        //给Province类型指定别名为province
        xStream.alias("province",Province.class);
        //给City类型指定别名为city
        xStream.alias("city", City.class);
        String s = xStream.toXML(proList);
        System.out.println(s);
    }
    /**
     * <china>
        <province>
            <name>北京</name>
            <cities>
                <city>
                    <name>东城区</name>
                    <description>DongChengQu</description>
                </city>
                <city>
                    <name>昌平区</name>
                    <description>ChangPingQu</description>
                </city>
            </cities>
        </province>
        <province>
            <name>辽宁</name>
            <cities>
                <city>
                    <name>沈阳</name>
                    <description>ShenYang</description>
                </city>
                <city>
                    <name>葫芦岛</name>
                    <description>HuLuDao</description>
                </city>
            </cities>
        </province>
     </china>
     */



    /**
     * 默认javabean的属性都会生成子元素，而现在希望生成元素的属性
     */
    @Test
    public void func3() {
        List<Province> proList = getProvinceList();

        XStream xStream = new XStream();

        /**
         * 给指定的类型指定别名
         */
        //给List类型指定别名为china
        xStream.alias("china", List.class);
        //给Province类型指定别名为province
        xStream.alias("province",Province.class);
        //给City类型指定别名为city
        xStream.alias("city", City.class);

        /**
         * 1. 把province的子元素name，生成<province>元素的属性
         */
        xStream.useAttributeFor(Province.class, "name");
        String s = xStream.toXML(proList);
        System.out.println(s);
    }
    /**
     * <china>
        <province name="北京">
            <cities>
                <city>
                    <name>东城区</name>
                    <description>DongChengQu</description>
                </city>
                <city>
                    <name>昌平区</name>
                    <description>ChangPingQu</description>
                </city>
            </cities>
        </province>
        <province name="辽宁">
            <cities>
                <city>
                    <name>沈阳</name>
                    <description>ShenYang</description>
                </city>
                <city>
                    <name>葫芦岛</name>
                    <description>HuLuDao</description>
                </city>
            </cities>
        </province>
     </china>
     */


    /**
     * 1. 去除<cities>元素
     */
    @Test
    public void func4() {
        List<Province> proList = getProvinceList();

        XStream xStream = new XStream();

        /**
         * 给指定的类型指定别名
         */
        //给List类型指定别名为china
        xStream.alias("china", List.class);
        //给Province类型指定别名为province
        xStream.alias("province",Province.class);
        //给City类型指定别名为city
        xStream.alias("city", City.class);


        /**
         * 让Cities类的，名为description属性不生成对应的xml元素
         */
        xStream.omitField(City.class, "description");
        String s = xStream.toXML(proList);
        System.out.println(s);
    }
    /**
     * <china>
        <province>
            <name>北京</name>
            <city>
                <name>东城区</name>
                <description>DongChengQu</description>
            </city>
            <city>
                <name>昌平区</name>
                <description>ChangPingQu</description>
            </city>
        </province>
        <province>
            <name>辽宁</name>
            <city>
                <name>沈阳</name>
                <description>ShenYang</description>
            </city>
            <city>
                <name>葫芦岛</name>
                <description>HuLuDao</description>
            </city>
        </province>
     </china>
     */



    /**
     * 1. 去除<cities>元素
     */
    @Test
    public void func5() {
        List<Province> proList = getProvinceList();

        XStream xStream = new XStream();

        /**
         * 给指定的类型指定别名
         */
        //给List类型指定别名为china
        xStream.alias("china", List.class);
        //给Province类型指定别名为province
        xStream.alias("province",Province.class);
        //给City类型指定别名为city
        xStream.alias("city", City.class);

        /**
         * 1. 去除<cities>这样的Collection类型的属性
         * 2. 去除Province类的名为cities的List类型的属性
         */
        xStream.addImplicitArray(Province.class, "cities");

        /**
         * 让Cities类的，名为description属性不生成对应的xml元素
         */
        xStream.omitField(City.class, "description");
        String s = xStream.toXML(proList);
        System.out.println(s);
    }

    /**
     * <china>
        <province>
            <name>北京</name>
            <city>
                <name>东城区</name>
            </city>
            <city>
                <name>昌平区</name>
            </city>
        </province>
        <province>
            <name>辽宁</name>
            <city>
                <name>沈阳</name>
            </city>
            <city>
                <name>葫芦岛</name>
                </city>
        </province>
     </china>
     */
}
