class BTreeNode:
    def __init__(self, t, leaf):
        self.t = t
        self.leaf = leaf
        self.keys = []
        self.values = []
        self.children = []

class BTreeMap:
    def __init__(self, t):
        self.t = t
        self.root = None

    def get(self, key):
        if self.root is None:
            return None
        else:
            return self._get(key, self.root)

    def _get(self, key, node):
        i = 0
        while i < len(node.keys) and key > node.keys[i]:
            i += 1
        if i < len(node.keys) and key == node.keys[i]:
            return node.values[i]
        elif node.leaf:
            return None
        else:
            return self._get(key, node.children[i])

    def set(self, key, value):
        if self.root is None:
            self.root = BTreeNode(self.t, True)
            self.root.keys.append(key)
            self.root.values.append(value)
        else:
            node = self.root
            if len(node.keys) == 2 * self.t - 1:
                new_root = BTreeNode(self.t, False)
                new_root.children.append(node)
                self._split_child(new_root, 0, node)
                node = new_root
                self.root = new_root
            self._set(key, value, node)

    def _set(self, key, value, node):
        i = 0
        while i < len(node.keys) and key > node.keys[i]:
            i += 1
        if node.leaf:
            node.keys.insert(i, key)
            node.values.insert(i, value)
        else:
            if len(node.children[i].keys) == 2 * self.t - 1:
                self._split_child(node, i, node.children[i])
                if key > node.keys[i]:
                    i += 1
            self._set(key, value, node.children[i])

    def _split_child(self, parent, i, child):
        new_child = BTreeNode(child.t, child.leaf)
        parent.children.insert(i + 1, new_child)
        parent.keys.insert(i, child.keys[self.t - 1])
        parent.values.insert(i, child.values[self.t - 1])
        new_child.keys = child.keys[self.t:]
        new_child.values = child.values[self.t:]
        child.keys = child.keys[:self.t - 1]
        child.values = child.values[:self.t - 1]
        if not child.leaf:
            new_child.children = child.children[self.t:]
            child.children = child.children[:self.t]

    def update(self, key, value):
        node = self.root
        while node is not None:
            i = 0
            while i < len(node.keys) and key > node.keys[i]:
                i += 1
            if i < len(node.keys) and key == node.keys[i]:
                node.values[i] = value
                return
            node = node.children[i]
        raise KeyError(key)


btree_map = BTreeMap(2)
btree_map.set(1, "one")
btree_map.set(2, "two")
btree_map.set(3, "three")
print(btree_map.get(1)) # выводит "one"
print(btree_map.get(2)) # выводит "two"
print(btree_map.get(3)) # выводит "three"
btree_map.update(2, "new two")
print(btree_map.get(2)) # выводит "new two"