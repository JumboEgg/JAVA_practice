import java.util.HashMap;
import java.util.ArrayList;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        
        //genre 총 횟수
        HashMap<String, Integer> genreCount = new HashMap<>();
        
        //genre별 곡
        HashMap<String, ArrayList<Integer>> playGenre = new HashMap<>();
        
        
        for(int i=0; i<plays.length; i++) {
            
            //처음 나오는 genre 처리
            if(genreCount.get(genres[i]) == null) {
                genreCount.put(genres[i], 0);
                playGenre.put(genres[i], new ArrayList<Integer>());
            }
            
            //genre 재생 횟수 저장
            genreCount.put(genres[i], genreCount.get(genres[i])+plays[i]);
            
            //genre별 곡 저장
            playGenre.get(genres[i]).add(i);
        }
        
        
        //앨범에 수록할 곡 저장
        ArrayList<Integer> album = new ArrayList<>();
        
        //genre 재생 횟수 내림차순으로 정렬
        ArrayList<String> genreSort = new ArrayList<>(genreCount.keySet());
        genreSort.sort((o1, o2) -> genreCount.get(o2).compareTo(genreCount.get(o1)));
        
        
        for(String g : genreSort) {
            
            if(playGenre.get(g).size() == 1) {
                album.add(playGenre.get(g).get(0));
                continue;
            }
            
            //genre별 곡 재생 횟수 내림차순으로 정렬
            playGenre.get(g).sort((o1, o2) -> {

                //재생 횟수가 같으면 고유번호가 작은 곡 return
                if(plays[o1] == plays[o2]) return o1 - o2;

                //재생 횟수가 다르면 많이 재생된 곡 return
                return plays[o2] - plays[o1];
            });
            
            album.add(playGenre.get(g).get(0));
            album.add(playGenre.get(g).get(1));
        }
        
        int[] answer = album.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}