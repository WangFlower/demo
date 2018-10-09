package com.example.cleveradapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static android.view.View.NO_ID;

public class CleverAdapter extends RecyclerView.Adapter<CleverViewHodler> {
    private HashMap<Object, Integer> typePool;
    private List<CleverModel<? extends CleverViewHodler>> models = new ArrayList<>();


    public void addModel(CleverModel<? extends CleverViewHodler> model){
        models.add(model);
    }

    public void addModels(List<CleverModel<? extends CleverViewHodler>> models){

    }


    public void removeModel(CleverModel<? extends CleverViewHodler> model){

    }

    public void updateModels(final List<CleverModel<? extends CleverViewHodler>> newMoldes){
        if(models.size()<=0){
            addModels(models);
        } else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return models.size();
                }

                @Override
                public int getNewListSize() {
                    return newMoldes.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    CleverModel<? extends CleverViewHodler> oldModel = getModel(models,oldItemPosition);
                    CleverModel<? extends CleverViewHodler> newModel = getModel(newMoldes ,newItemPosition);
                    return oldModel != null && newModel != null
                            && oldModel.getClass().equals(newModel.getClass())
                            && oldModel.isTheSameItem(newModel);
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    CleverModel<? extends CleverViewHodler> oldModel = getModel(models,oldItemPosition);
                    CleverModel<? extends CleverViewHodler> newModel = getModel(newMoldes ,newItemPosition);
                    return oldModel != null && newModel != null
                            && oldModel.getClass().equals(newModel.getClass())
                            && oldModel.areContentsTheSame(newModel);
                }

                private CleverModel<? extends CleverViewHodler> getModel(List<CleverModel<? extends CleverViewHodler>> list,int index){
                    return (list != null && index >= 0 && index < list.size())
                            ? list.get(index) : null;
                }
            });
            models.clear();
            models.addAll(newMoldes);
            diffResult.dispatchUpdatesTo(this);
        }
    }


    @Override
    public CleverViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(CleverViewHodler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }





}
