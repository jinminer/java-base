package com.jinm.deepinjava.objectoriented.design;

import java.util.ArrayList;
import java.util.List;

public class SnapshotDemo {

    private static class Data{

        List<String> data;

        //把内部的状态暴露到外部，外部可以对状态进行修改
        public List<String> getData() {
            return data;
        }

        public void setData(List<String> data) {
            this.data = data;
        }
    }

    private static class SnapshotData{

        List<String> data;

        //将类状态复制一份再暴露出去，相对于快照，不会使状态发生改变
        public List<String> getData() {
            return new ArrayList<String>(data);
        }

        public void setData(List<String> data) {
            this.data = data;
        }

    }


}
