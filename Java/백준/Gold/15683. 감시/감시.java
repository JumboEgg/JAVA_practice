import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	
	public static int N, M;
	public static int[][] office;
	public static int[] dx = {0, 1, 0, -1};
	public static int[] dy = {1, 0, -1, 0};
	public static int[][] camera;
    public static ArrayList<Integer> results;
	
    public static void main(String[] args) throws Exception {
        

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        // 결과를 한 번에 출력하기 위한 StringBuilder
        StringBuilder sb = new StringBuilder();
        
        String[] temp = in.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        int count = 0;
        results = new ArrayList<>();
        
        //office 정보 입력
        String[] input = new String[M];
        office = new int[N][M];
        for(int i=0; i<N; i++) {
        	input = in.readLine().split(" ");
        	for(int j=0; j<M; j++) {
        		office[i][j] = Integer.parseInt(input[j]);
        		if(office[i][j] != 0 && office[i][j] != 6) {
        			count++;
        		}
        	}
        }
        
        //CCTV의 Y, X, 종류 저장
        camera = new int[count][3];
        count = 0;
        for(int i=0; i<N; i++) {
        	for(int j=0; j<M; j++) {
        		if(office[i][j] != 0 && office[i][j] != 6) {
        			camera[count][0] = i;
        			camera[count][1] = j;
        			camera[count][2] = office[i][j];
        			count++;
        		}
        	}
        }
        int[][] test = new int[N][M];
        initializeArray(office, test);
        testView(camera.length-1, test);
        
        int result = Collections.min(results);
        
        sb.append(result).append("\n");

        System.out.println(sb);
    }
    
    //주어진 Array에서 감시 시도하기
    public static void testView(int cameraNum, int[][] test) {
    	int[][] backup = new int[N][M];
    	initializeArray(test, backup);
    	
    	if(cameraNum < 0) {
    		results.add(countZero(test));
    		return;
    	}
    	switch(camera[cameraNum][2]) {
    	case 1:
    		for(int i=0; i<4; i++) {
        		searchDir(camera[cameraNum][0], camera[cameraNum][1], camera[cameraNum][2], i, test);
        		testView(cameraNum - 1, test);
        		initializeArray(backup, test);
    		}
    		break;
    	case 2:
    		for(int i=0; i<2; i++) {
        		searchDir(camera[cameraNum][0], camera[cameraNum][1], camera[cameraNum][2], i, test);
        		testView(cameraNum - 1, test);
        		initializeArray(backup, test);
    		}
    		break;
    	case 3:
    		for(int i=0; i<4; i++) {
        		searchDir(camera[cameraNum][0], camera[cameraNum][1], camera[cameraNum][2], i, test);
        		testView(cameraNum - 1, test);
        		initializeArray(backup, test);
    		}
    		break;
    	case 4:
    		for(int i=0; i<4; i++) {
        		searchDir(camera[cameraNum][0], camera[cameraNum][1], camera[cameraNum][2], i, test);
        		testView(cameraNum - 1, test);
        		initializeArray(backup, test);
    		}
    		break;
    	case 5:
    		searchDir(camera[cameraNum][0], camera[cameraNum][1], camera[cameraNum][2], 0, test);
    		testView(cameraNum - 1, test);
    		initializeArray(backup, test);
    		break;
    	}
    }
    
    //test Array 초기화
    public static void initializeArray(int[][] ref, int[][] copy) {
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<M; j++) {
    			copy[i][j] = ref[i][j];
    		}
    	}
    }
    
    //Array 내 사각지대 count
    public static int countZero(int[][] test) {
        int count = 0;
        for(int i=0; i<N; i++) {
        	for(int j=0; j<M; j++) {
        		if(test[i][j] == 0) {
        			count++;
        		}
        	}
        }
        return count;
    }
    
    //카메라 종류에 따라 감시 영역 표시
    public static void searchDir(int y, int x, int num, int dir, int[][] test) {
    	switch(test[y][x]) {
    	case 1:
    		viewArea(y, x, dir, test);
    		break;
    	case 2:
    		viewArea(y, x, dir, test);
    		viewArea(y, x, (dir+2)%4, test);
    		break;
    	case 3:
    		viewArea(y, x, dir, test);
    		viewArea(y, x, (dir+1)%4, test);
    		break;
    	case 4:
    		viewArea(y, x, dir, test);
    		viewArea(y, x, (dir+1)%4, test);
    		viewArea(y, x, (dir+3)%4, test);
    		break;
    	case 5:
    		viewArea(y, x, dir, test);
    		viewArea(y, x, (dir+1)%4, test);
    		viewArea(y, x, (dir+2)%4, test);
    		viewArea(y, x, (dir+3)%4, test);
    		break;
    	}
    }
    
    //직선 경로의 감시 영역 표시
    public static void viewArea(int y, int x, int dir, int test[][]) {
		while(y+dy[dir] < N && y+dy[dir] >= 0
				&& x+dx[dir] < M && x+dx[dir] >= 0
				&& test[y+dy[dir]][x+dx[dir]] != 6) {
			y += dy[dir];
			x += dx[dir];
			if(test[y][x] != 1 && test[y][x] != 2 && test[y][x] != 3 
				&& test[y][x] != 4 && test[y][x] != 5 && test[y][x] != 7) {
				test[y][x] = 7;
			}
		}
    }
}
