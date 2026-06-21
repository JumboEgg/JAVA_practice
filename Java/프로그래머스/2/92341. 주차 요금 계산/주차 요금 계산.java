import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {

        // 현재 입차 중인 차량
        Map<String, Integer> inTime = new HashMap<>();

        // 차량별 누적 주차 시간
        Map<String, Integer> totalTime = new TreeMap<>();

        for (String record : records) {
            String[] rec = record.split(" ");

            String time = rec[0];
            String car = rec[1];
            String type = rec[2];

            int minute = toMinute(time);

            if ("IN".equals(type)) {
                inTime.put(car, minute);
            } else {
                int in = inTime.remove(car);

                totalTime.put(
                    car,
                    totalTime.getOrDefault(car, 0) + (minute - in)
                );
            }
        }

        // 출차 기록 없는 차량 처리 (23:59 출차)
        int end = toMinute("23:59");

        for (String car : inTime.keySet()) {
            int in = inTime.get(car);

            totalTime.put(
                car,
                totalTime.getOrDefault(car, 0) + (end - in)
            );
        }

        int[] answer = new int[totalTime.size()];

        int idx = 0;
        for (int time : totalTime.values()) {
            answer[idx++] = calcFee(time, fees);
        }

        return answer;
    }

    private int toMinute(String time) {
        String[] t = time.split(":");

        int h = Integer.parseInt(t[0]);
        int m = Integer.parseInt(t[1]);

        return h * 60 + m;
    }

    private int calcFee(int time, int[] fees) {

        int basicTime = fees[0];
        int basicFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];

        if (time <= basicTime) {
            return basicFee;
        }

        return basicFee
            + (int)Math.ceil((double)(time - basicTime) / unitTime) * unitFee;
    }
}