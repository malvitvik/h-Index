import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimesCited {
	
	private List<Integer> times = new ArrayList<>();
	int hIndex = 1;

	public static void main(String[] args) {
		int[] timesCited = {0, 0, 20, 0, 1, 8, 7, 8, 9, 7, 9, 8, 10, 10, 12, 3, 4, 8, 8, 9, 9, 8, 8};
		int[] timesCited2 = Arrays.copyOf(timesCited, timesCited.length);
		int[] timesCited3 = Arrays.copyOf(timesCited, timesCited.length);
		Arrays.sort(timesCited2);

		System.out.println(hIndex1(timesCited));
		System.out.println(Arrays.toString(timesCited));
		System.out.println(hIndex2(timesCited2));
		System.out.println();

		TimesCited tCited = new TimesCited();

		for (int cited : timesCited3) {
			System.out.printf("%,2d has hIndex = %d\n", cited, tCited.hIndex(cited));
		}		
	}


	public int hIndex(int cited) {
		if (hIndex < 1) {
			hIndex = 1;
		}

		times.add(cited);
		times.sort(null);
		
		for (int i = times.size() - hIndex; i >= 0; i--) {
			if (times.get(i) < hIndex) {
				return --hIndex;
			}
			hIndex++;
		}

		return --hIndex;
	}


	public static int hIndex1(int[] timesCited) {
		Arrays.sort(timesCited);

		int h = 1;
		for (int i = timesCited.length - 1; i >= 0; i--) {
			if (timesCited[i] < h) {
				return h - 1;
			}
			h++;
		}
		return 0;
	}


	private static int hIndex2(int[] timesCited) {
		return helper(timesCited, 0, timesCited.length - 1);
	}


	private static int helper(int[] timesCited, int low, int high) {
		if (low + 1 >= high)
			return timesCited.length - high;
		
		int mid = (high - low) / 2 + low;

		if (timesCited[mid] >= timesCited.length - mid) {
			return helper(timesCited, low, mid);
		}
		
		return helper(timesCited, mid, high);
	}

}