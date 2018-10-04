package com.example.lib.thread;

public class DeadLockDemo {


    public class Account{

        public void debit(int money){

        }

        public void credit(int money){

        }

    }

    private static final Object tieLock = new Object();


    public void transferMoney(final Account fromAcct,final Account toAcct,final int money){
        class Helper{
            public void tranfer(){
                fromAcct.debit(money);
                toAcct.credit(money);
            }
        }

        int fromHash = System.identityHashCode(fromAcct);
        int toHash = System.identityHashCode(toAcct);
        if(fromHash<toHash){
            synchronized (fromAcct){
                synchronized (toAcct){
                    new Helper().tranfer();
                }
            }
        } else if(fromHash>toHash){
            synchronized (toAcct){
                synchronized (fromAcct){
                    new Helper().tranfer();
                }
            }
        } else {
            synchronized (tieLock){
                synchronized (fromAcct){
                    synchronized (toAcct){
                        new Helper().tranfer();
                    }
                }
            }
        }




    }
}
