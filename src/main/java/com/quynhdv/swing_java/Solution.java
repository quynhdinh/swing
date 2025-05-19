package com.quynhdv.swing_java;
import java.util.*;

class Solution {
    public List<Pair<Integer, Integer>> maxRectangleArea(List<Integer> xCoord, List<Integer>  yCoord) {
        int n = xCoord.size();

        TreeMap<Integer, List<Integer>> xMap = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            xMap.putIfAbsent(xCoord.get(i), new ArrayList<>());
            xMap.get(xCoord.get(i)).add(yCoord.get(i));
        }

        Map<String, int[]> coordinatePairMap = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : xMap.entrySet()) {
            int x = entry.getKey();
            List<Integer> yList = entry.getValue();
            Collections.sort(yList);
            for (int i = 0; i < yList.size() - 1; i++) {
                coordinatePairMap.put(x + "_" + yList.get(i), new int[]{x, yList.get(i + 1)});
            }
        }

        Map<Integer, Integer> yxMap = new HashMap<>();
        TreeSet<Integer> sortedY = new TreeSet<>();
        long result = -1;
        int xUpperLeft = -1, yUpperLeft = -1, xLowerRight = -1, yLowerRight = -1;
        for (Map.Entry<Integer, List<Integer>> entry : xMap.entrySet()) {
            int x = entry.getKey();
            List<Integer> yList = entry.getValue();
            for (int i = 0; i < yList.size() - 1; i++) {
                int y1 = yList.get(i);
                int y2 = yList.get(i + 1);
                if (sortedY.contains(y1)) {
                    int prevX = yxMap.get(y1);
                    String key = prevX + "_" + y1;
                    if (coordinatePairMap.containsKey(key) && coordinatePairMap.get(key)[1] == y2) {
                        long new_area = (long)(y2 - y1) * (x - prevX);
                        if(new_area > result){
                            result = new_area;
                            xLowerRight = x;
                            yLowerRight = y1;
                            xUpperLeft = prevX;
                            yUpperLeft = y2;
                        }
                    }
                }
            }

            for (int y : yList) {
                while (!sortedY.isEmpty()) {
                    Integer remY = sortedY.floor(y);
                    if (remY == null) {
                        break;
                    }
                    int remX = yxMap.get(remY);
                    String key = remX + "_" + remY;
                    if (coordinatePairMap.containsKey(key) && coordinatePairMap.get(key)[1] >= y) {
                        sortedY.remove(remY);
                    } else {
                        break;
                    }
                }
            }
            for (int i = 0; i < yList.size() - 1; i++) {
                sortedY.add(yList.get(i));
                yxMap.put(yList.get(i), x);
            }
        }
        List<Pair<Integer, Integer>> res = new ArrayList<>();
        res.add(new Pair<>(xUpperLeft, yUpperLeft));
        res.add(new Pair<>(xLowerRight, yLowerRight));
        return res;
    }
}