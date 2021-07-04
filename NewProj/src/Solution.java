import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'order' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. UNWEIGHTED_INTEGER_GRAPH city
     *  2. INTEGER company
     */

    /*
     * For the unweighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] and <name>To[i].
     *
     */

    public static List<Integer> order(int cityNodes, List<Integer> cityFrom, List<Integer> cityTo, int company) {
        Map<Integer, Node> intNodeMap = new HashMap<>();
        for(int i = 0; i<cityNodes; i++){
            int nodeCount = i+1;
            Node n = new Node(nodeCount);
            intNodeMap.put(nodeCount, n);
        }

        for(int i = 0; i<cityFrom.size();i++){
            Node fromNode = intNodeMap.get(cityFrom.get(i));
            Node toNode = intNodeMap.get(cityTo.get(i));

            fromNode.neighbours.add(toNode);
            toNode.neighbours.add(fromNode);
        }

        Node startNode = intNodeMap.get(company);
        Map<Integer, TreeSet<Integer>> result = new TreeMap<>();
        LinkedList<Node> queue = new LinkedList<>();
        Set<Node> isVisited = new HashSet<>();

        queue.add(startNode);
        isVisited.add(startNode);

        while(!queue.isEmpty()){
            Node currNode = queue.poll();
            for(Node neighbour : currNode.neighbours){
                if(!isVisited.contains(neighbour)){
                    neighbour.level = currNode.level+1;
                    isVisited.add(neighbour);
                    if(!result.containsKey(neighbour.level)){
                        result.put(neighbour.level, new TreeSet<>());
                    }
                    result.get(neighbour.level).add(neighbour.val);
                    queue.add(neighbour);
                }
            }
        }
        LinkedList<Integer> res = new LinkedList<>();
        result.forEach((k,v)->{
            for(int neighbour : v){
                res.add(neighbour);
            }
        });
        return res;
    }

    private static class Node{
        int val;
        Set<Node> neighbours;
        int level;

        public Node(int val){
            this.val = val;
            neighbours = new TreeSet<>(Comparator.comparingInt(Node::getVal));
            level = 0;
        }

        public int getVal(){
            return val;
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] cityNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int cityNodes = Integer.parseInt(cityNodesEdges[0]);
        int cityEdges = Integer.parseInt(cityNodesEdges[1]);

        List<Integer> cityFrom = new ArrayList<>();
        List<Integer> cityTo = new ArrayList<>();

        IntStream.range(0, cityEdges).forEach(i -> {
            try {
                String[] cityFromTo = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                cityFrom.add(Integer.parseInt(cityFromTo[0]));
                cityTo.add(Integer.parseInt(cityFromTo[1]));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int company = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> result = Result.order(cityNodes, cityFrom, cityTo, company);

        bufferedWriter.write(
                result.stream()
                      .map(Object::toString)
                      .collect(joining("\n"))
                + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
