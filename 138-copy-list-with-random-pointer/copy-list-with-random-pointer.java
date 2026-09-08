class Solution {
    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }

        HashMap<Node, Node> m = new HashMap<>();

        // First pass: create copies
        Node oldTemp = head;

        while (oldTemp != null) {
            Node copyNode = new Node(oldTemp.val);
            m.put(oldTemp, copyNode);
            oldTemp = oldTemp.next;
        }

        // Second pass: connect next and random
        oldTemp = head;

        while (oldTemp != null) {

            Node copyNode = m.get(oldTemp);

            copyNode.next = m.get(oldTemp.next);
            copyNode.random = m.get(oldTemp.random);

            oldTemp = oldTemp.next;
        }

        return m.get(head);
    }
}