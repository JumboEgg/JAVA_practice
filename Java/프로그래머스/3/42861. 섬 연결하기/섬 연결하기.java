import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int n, int[][] costs) {
        //비용 순으로 정렬
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        
        //자신과 연결된 섬 번호 저장
        int[] con = new int[n];
        
        //연결 정보 초기화
        for(int i=0; i<n; i++) {
            con[i] = i;
        }
        
        int answer = 0;
        
        //연결되지 않은 섬을 최소 비용으로 연결
        for(int i=0; i<costs.length; i++) {
            
            //이미 연결된 섬일 경우
            if(findConNum(con, costs[i][0])
                == findConNum(con, costs[i][1])) {
                continue;
            }
            
            //연결되지 않은 섬일 경우 다리 건설
            answer += costs[i][2]; //건설 비용 반영
            
            //연결 정보 갱신
            connect(con, costs[i][0], costs[i][1]);
        }
        
        return answer;
    }
    
    int findConNum(int[] con, int island) {
        //대표 섬 도달
        if(con[island] == island) return island;
        
        //탐색한 섬을 대표 섬에 직접 연결
        return con[island] = findConNum(con, con[island]);
    }
    
    void connect(int[] con, int island1, int island2) {
        int rep1 = findConNum(con, island1);
        int rep2 = findConNum(con, island2);
        
        con[rep2] = rep1;
    }
}