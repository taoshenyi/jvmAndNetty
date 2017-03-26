package com.unionfin.algorithm;

public class FinalizeEscapeGC
{
    public static FinalizeEscapeGC SAVE_HOOK = null;
    private static final int _1MB = 1024 * 1024;
    private byte[] bigSize = new byte[2 * _1MB];


    public void isAlive()
    {
        System.out.println("yes,i am still alive");
    }


    @Override
    protected void finalize() throws Throwable
    {
        super.finalize();
        System.out.println("finalize method executed");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }


    public static void main(String[] args) throws InterruptedException
    {
        SAVE_HOOK = new FinalizeEscapeGC();
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        System.out.println("*************************************");
        if (SAVE_HOOK != null)
        {
            SAVE_HOOK.isAlive();
        }
        else
        {
            System.out.println("no,i am dead:");
        }
        /*
         * SAVE_HOOK = null; System.gc(); Thread.sleep(500);
         * System.out.println("*********************************************");
         * if (SAVE_HOOK != null) { SAVE_HOOK.isAlive(); } else {
         * System.out.println("no,i am dead:"); }
         */
    }

}
