
package edu.svu.csc326;

/**
 * Implementation of a matrix for a graph.
 * @author Heidi
 */
public class GraphMatrix {

    int size;
    Double[][] matrix;
/**
 * Constructor creates the size of the matrix.
 * @param size the number of rows in the matrix.
 */
    public GraphMatrix(int size) {
        this.size = size;
        matrix = new Double[size][size];
    }
/**
 * Adds a Node into the matrix.
 * @param connected ArrayList containing indices of all connected nodes.
 * @param number the index of the node.
 * @param weight the weight associated with all connected nodes.
 * @return the modified Matrix.
 */
    public GraphMatrix add(ArrayList<Integer> connected, int number, ArrayList<Double> weight) {
        for (int i = 0; i < connected.getLength(); ++i) {
            matrix[number][connected.retrieve(i)] = weight.retrieve(i);
            matrix[connected.retrieve(i)][number] = weight.retrieve(i);
        }
        return this;
    }

    public boolean isAdjacent(int one, int two) {
        return matrix[one][two] != 0;

    }
/**
 * Maps out the shortest path between the start and end.
 * @param start the matrix index of the starting node.
 * @param finish the matrix index of the ending node
 * @return an ArrayList containing all the indices of the nodes in the shortest path.
 */
    public ArrayList shortestPath(int start, int finish) {
        ArrayList<ArrayList> paths = new ArrayList();
        ArrayList<Double> lengths = new ArrayList();
        boolean solved = false;
        paths.append((ArrayList) new ArrayList().append(start));
        lengths.append(0.0);
        while (!solved) {
            ArrayList currentList = paths.retrieve(0);
            int nodeNumber = (Integer) currentList.retrieve(currentList.getLength() - 1);
            for (int i = 0; i < size; ++i) {
                double value = matrix[nodeNumber][i];
                if (value != 0 && currentList.contains(i) == -1) {
                    ArrayList list2 = new ArrayList(currentList);
                    list2.append(i);
                    double newLength = lengths.retrieve(0) + value;
                    lengths.append(newLength);
                    paths.append(list2);
                    sort(paths, lengths);
                    if (paths.retrieve(0).contains(finish) != -1) {
                        solved = true;
                    }
                }
            }
        }
//        ArrayList<ArrayList> data = new ArrayList(2);
//        data.append(paths.retrieve(0)).append(lengths.retrieve(0));
        return paths.retrieve(0);
    }
/**
 * Sorts both the lengths and path arrays, in order to keep the path
 * with the shortest length in index 0.
 * @param paths
 * @param lengths 
 */
    private void sort(ArrayList paths, ArrayList<Double> lengths) {
        boolean done = false;
        while (!done) {
            done = true;
            for (int i = 0; i < lengths.getLength() - 1; i++) {
                if (lengths.retrieve(i) > lengths.retrieve(i + 1)) {
                    swap(i, i + 1, paths);
                    swap(i, i + 1, lengths);
                    done = false;

                }

            }

        }
    }

    private void swap(int i, int j, ArrayList list) {
        Object temp = list.retrieve(i);
        list.replace(i, list.retrieve(j));
        list.replace(j, temp);

    }
}
