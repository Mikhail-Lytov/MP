import gc
import copy


class BTreeNode:
    def __init__(self, t, leaf):
        self.t = t
        self.leaf = leaf # проверки на лист
        self.keys = []
        self.values = []
        self.children = []
    def __del__(self):
        print("object Node destroyed")

class BTreeMap: # ассациативного массив
    def __init__(self, t):
        print("Object init")
        self.t = t
        self.root = None

    def get(self, key): # возращает значение по ключу
        if self.root is None:
            return None
        else:
            return self.__get(key, self.root)

    def __get(self, key, node): # поиск по ключу
        i = 0
        while i < len(node.keys) and key > node.keys[i]:
            i += 1
        if i < len(node.keys) and key == node.keys[i]:
            return node.values[i]
        elif node.leaf:
            return None
        else:
            return self.__get(key, node.children[i])

    def set(self, key, value): # добавление элемента в дерево
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
            self.__set(key, value, node)

    def __set(self, key, value, node):
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
            self.__set(key, value, node.children[i])

    def _split_child(self, parent, i, child): #перестройка дерева
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

    def update(self, key, value): # изменение значения ключа
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

    def check_emptiness(self):
        node = self.root
        while node is not None:
            return False
        return True

    def delete_all(self):
        self.root = None
        gc.collect()#собирает полный мусор
        gc.garbage

    def __del__(self):
        print("Object destroyed")

    def copy(self):
        btree_map = copy.deepcopy(self)
        return btree_map


btree_map = BTreeMap(2)
btree_map.set(3, "three")
btree_map.set(2, "two")
btree_map.set(4, "four")
btree_map.set(5, "five")
btree_map.set(1, "one")
print(btree_map.get(1))
print(btree_map.get(2))
print(btree_map.get(3))
print(btree_map.get(5))
print()
print("Копирование")
copy_btree_map = btree_map.copy()
copy_btree_map.update(2,"FILE")
print("оригинал: " + btree_map.get(2))
print("копия: " + copy_btree_map.get(2))
copy_btree_map.delete_all()
print("Список пуст? " + str(btree_map.check_emptiness()))
print("количество объектов до удаления в мусорке")
print(btree_map.delete_all())
print("Список пуст? " + str(btree_map.check_emptiness()))
print("количество элементов после освобожодения")
print(btree_map.delete_all())
