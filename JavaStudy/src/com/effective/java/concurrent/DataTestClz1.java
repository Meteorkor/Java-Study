
package com.effective.java.concurrent;

/**
 * @author unseok.kim
 * @since 2017. 5. 31.
 */
public class DataTestClz1 {
    private String ename;
    private int empno;

    public DataTestClz1(String ename, int empno) {
        this.ename = ename;
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public int getEmpno() {
        return empno;
    }

}
