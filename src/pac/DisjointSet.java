public class DisjointSet {
    int[] rank, parent;

    public DisjointSet(int n) {
        rank = new int[n+1];
        parent = new int[n+1];
        for(int i=0;i<=n;i++) {
            parent[i] = i;
        }
    }

    public int findUParent(int node) {
        if(node == parent[node]) {
            return node;
        }
        return parent[node] = findUParent(parent[node]);
    }

    public void unionByRank(int u,int v) {
        int parentU = findUParent(u);
        int parentV = findUParent(v);

        if(parentU == parentV) {
            return;
        }
        if(rank[parentU] < rank[parentV]) {
            parent[parentU] = parentV;
        }
        else if(rank[parentV] < rank[parentU]) {
            parent[parentV] = parentU;
        }
        else {
            parent[parentV] = parentU;
            rank[parentU]++;
        }
    }
}
