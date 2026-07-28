class Solution {
    public int shortestPath(int V, int src, int dest, int[][] edges) {
        List<int[]>[] adj = new List[V];
        for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
            adj[e[1]].add(new int[]{e[0], e[2]});
        }

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(src);

        while (!deque.isEmpty()) {
            int u = deque.pollFirst();
            for (int[] nb : adj[u]) {
                int v = nb[0], w = nb[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    if (w == 1) deque.addFirst(v);
                    else deque.addLast(v);
                }
            }
        }

        return dist[dest] == Integer.MAX_VALUE ? -1 : dist[dest];
    }
}