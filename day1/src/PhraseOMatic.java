public class PhraseOMatic {
    public static void main(String[] args){
        String[] wordListOne = {"I", "You", "He", "She", "Her mother", "Her father", "His mother", "His father"};
        String[] wordListTwo = {"like(s)", "love(s)", "hate(s)", "do(es)n't like", "do(es)n't hate"};
        String[] wordListThree = {"me", "you", "him", "her", "her mother", "her father", "himself", "herself", "her mother", "her father"};

        int pos1, pos2, pos3;
        int len1 = wordListOne.length, len2 = wordListTwo.length, len3 = wordListThree.length;
        pos1 = (int)(len1 * Math.random());
        pos2 = (int)(len2 * Math.random());
        pos3 = (int)(len3 * Math.random());

        System.out.println("What we need is: " + wordListOne[pos1] + " " + wordListTwo[pos2] + " " + wordListThree[pos3] + ".");
    }
}
