

import java.util.ArrayList;

public class KeywordList {
	private ArrayList<Keyword> lst;
	
	public KeywordList(){
		this.lst = new ArrayList<Keyword>();
    }
	
	public void add(Keyword keyword){
		lst.add(keyword);
		System.out.println("Done");
    }
	
	public void find(String s){
		int maxValue = -1;
		int maxIndex = -1;
		for(int i=0; i<lst.size(); i++){
			int lcs = findLCS(lst.get(i).name, s);
			System.out.println(lcs);
			if(lcs > maxValue){
				maxValue = lcs;
				maxIndex = i;
			}
		}
		System.out.println(lst.get(maxIndex).toString());
	}
	
	public int findLCS(String x, String y){
		//fill this method
		int n=x.length()+1;
		int m = y.length()+1;
		int[][] matrix = new int[n][m];
		for(int i=0;i<n;i++) {
			matrix[i][0]=0;
		}
		for(int j=0;j<m;j++) {
			matrix[0][j]=0;
		}
		for(int i=1;i<n-1;i++) {
			for(int j=1;j<m-1;j++) {
				if(x.charAt(i-1)==y.charAt(j-1)) {
					matrix[i][j]=matrix[i-1][j-1]+1;
				}else {
					if(matrix[i-1][j]>matrix[i][j-1]) {
						matrix[i][j]=matrix[i-1][j];
					}else {
						matrix[i][j]=matrix[i][j-1];
					}
					
				}
			}
		}
		
		return matrix[n-1][m-1];
		
	}
	
	
	private void printMatrix(int[][] matrix){
		for(int i=0; i<matrix.length; i++){
			for(int j=0; j<matrix[0].length; j++){
				System.out.print(matrix[i][j] + " ");
				if(j==matrix[0].length-1)System.out.print("\n");
			}
		}
	}
}