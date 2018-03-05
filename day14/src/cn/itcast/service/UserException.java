package cn.itcast.service;

/**
 * 自定义一个异常类
 * 只是给出父类的构造器即可！方便来创建对象！
 * @author king
 */
public class UserException extends Exception{
    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    public UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
