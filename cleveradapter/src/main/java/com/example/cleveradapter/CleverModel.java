package com.example.cleveradapter;

public abstract class CleverModel<VH extends CleverViewHodler>{
    private static long idCounter = -1;

    private long id;


    public CleverModel(){
        this(idCounter--);
    }

    public CleverModel(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

    abstract int getViewType();

    abstract int getItemViewLayoutId();





    boolean isTheSameItem(CleverModel<? extends CleverViewHodler> newModel){
        return this.id==newModel.getId();
    }

    boolean areContentsTheSame(CleverModel<? extends CleverViewHodler> newModel){
        return true;
    }


    public void onCreate(){

    }

    public void onBinder(){

    }

    public void onRecycler(){

    }




}
