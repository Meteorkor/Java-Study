
package com.effective.java.concurrent;

/**
 * @author unseok.kim
 * @since 2017. 5. 31.
 */
public class EffectivelyImmutable {
    //방지방법1, 객체 참조를 클래스 초기화시에 static필드에 저장
    {//객체 참조를 클래스 초기화시에 static필드에 저장
        DataTestClz1 localVar = new DataTestClz1("kim",11);
        firstSolv = localVar;
    }
    private static DataTestClz1 firstSolv;

    public static DataTestClz1 getFirstSolv() {
        return firstSolv;
    }

    /**
     * 방지방법3, volatile 필드에 저장
     * 
     * @since  2017. 5. 31.
     * @param name
     * @param number
     */
    private volatile DataTestClz1 dataClz1 = new DataTestClz1("default",0);
    public DataTestClz1 getDataClz2(){
        return this.dataClz1;
    } 
    
    public void setDataClz1(String name, int number){
        DataTestClz1 localdataClz1 = new DataTestClz1(name,number);
        dataClz1 = localdataClz1;
    }
    
    //방지방법4, final 필드 활용
    {
        finalVar = new DataTestClz1("lee", 22);
    }
    public final DataTestClz1 finalVar;
}
