package com.example.app7.RoomDatabase;

import com.example.app7.OrderItemModel;

public interface AddRemoveQuantityClickInterfce {
    void removeClicked(OrderItemModel data,int position);
    void addClicked(OrderItemModel data,int position);
}
