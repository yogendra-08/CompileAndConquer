import java.util.*;

class Solution {
    public List<List<String>> findLadders(
            String beginWord, String endWord, List<String> wordList) {

        List<List<String>> result = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);

        if (!dict.contains(endWord)) return result;

        Map<String, Integer> distance = new HashMap<>();
        Map<String, List<String>> parents = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(beginWord);
        distance.put(beginWord, 0);

        int shortest = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            String word = queue.poll();
            int dist = distance.get(word);

            if (dist >= shortest) continue;

            char[] chars = word.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                char original = chars[i];

                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == original) continue;
                    chars[i] = c;
                    String next = new String(chars);

                    if (!dict.contains(next)) continue;

                    if (!distance.containsKey(next)) {
                        distance.put(next, dist + 1);
                        queue.offer(next);
                    }

                    if (distance.get(next) == dist + 1) {
                        parents.computeIfAbsent(next, k -> new ArrayList<>())
                               .add(word);

                        if (next.equals(endWord)) {
                            shortest = dist + 1;
                        }
                    }
                }

                chars[i] = original;
            }
        }

        if (!distance.containsKey(endWord)) return result;

        List<String> path = new ArrayList<>();
        path.add(endWord);
        buildPaths(endWord, beginWord, parents, path, result);

        return result;
    }

    private void buildPaths(String word, String beginWord,
                            Map<String, List<String>> parents,
                            List<String> path, List<List<String>> result) {
        if (word.equals(beginWord)) {
            List<String> copy = new ArrayList<>(path);
            Collections.reverse(copy);
            result.add(copy);
            return;
        }

        for (String parent : parents.getOrDefault(word, Collections.emptyList())) {
            path.add(parent);
            buildPaths(parent, beginWord, parents, path, result);
            path.remove(path.size() - 1);
        }
    }
}