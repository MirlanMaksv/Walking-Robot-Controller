package com.maksitaliev.mirlan.walkingrobotcontroller.view.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Unbinder;

import static com.maksitaliev.mirlan.walkingrobotcontroller.Constants.KEY_LAYOUT;

/**
 * Created by mirlan on 09.03.18.
 */

public class BaseFragment extends Fragment {

    protected String TAG = getClass().getSimpleName();
    protected Unbinder unbinder;
    protected Activity activity;

    public static <T extends BaseFragment> T newInstance(Class<T> clazz, int layout) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_LAYOUT, layout);
        try {
            T fragment = clazz.newInstance();
            fragment.setArguments(bundle);
            return fragment;
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Log.e(TAG, "onCreateView()");
        int layout = getArguments().getInt(KEY_LAYOUT);
        return inflater.inflate(layout, container, false);
    }

    protected void setUnbinder(Unbinder unbinder) {
        this.unbinder = unbinder;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = getActivity();
//        Log.e(TAG, "onAttach()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        Log.e(TAG, "onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        Log.e(TAG, "onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (unbinder != null)
            unbinder.unbind();

        activity = null;
//        Log.e(TAG, "onDetach()");
    }
}
