package recursion;

//Count All Subsets Of A Set Of Size N
public class CountAllSubsets {
    public static void main(String[] args) {
        int n = 3;
        System.out.println(count_all_subsets1(n));
    }

    static Integer count_all_subsets(Integer n) {

        if (n == 0) {
            return 1;
        }

        int subsets_count = count_all_subsets(n / 2) * count_all_subsets(n / 2);

        if (n % 2 != 0) {
            subsets_count *= 2;
        }

        return subsets_count;
    }

    static Integer count_all_subsets1(Integer n) {

        if (n == 0) {
            return 1;
        }
        return 2 * count_all_subsets1(n - 1);

    }
}
