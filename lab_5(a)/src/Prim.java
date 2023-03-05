import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;

class NodeCost {

    int node;
    int cost;

    NodeCost (int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
}

class Prims {

    int Find_MST(int source_node, List<List<NodeCost>> graph) {


        Comparator<NodeCost> NodeCostComparator = (obj1, obj2) -> {
            return obj1.cost - obj2.cost;
        };


        PriorityQueue<NodeCost> pq = new PriorityQueue<>(NodeCostComparator);


        pq.add(new NodeCost(source_node, 0));

        boolean added[] = new boolean[graph.size()];
        Arrays.fill(added, false);

        int mst_cost = 0;

        while (!pq.isEmpty()) {


            NodeCost item = pq.peek();
            pq.remove();

            int node = item.node;
            int cost = item.cost;

            if (!added[node]) {
                mst_cost += cost;
                added[node] = true;

                for (NodeCost pair_node_cost : graph.get(node)) {
                    int adj_node = pair_node_cost.node;
                    if (added[adj_node] == false) {
                        pq.add(pair_node_cost);
                    }
                }
            }
        }
        return mst_cost;
    }
}