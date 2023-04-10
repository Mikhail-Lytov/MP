class Node:
    def __init__(self, key, value):
        self.key = key
        self.next = None
        self.value = value


class HashMap:
    def __init__(self):
        self.capacity = 50
        self.size = 0
        self.container = [None]*self.capacity

    def hash(self, key):
        hashsum = 0

        for idx, c in enumerate(key):
            hashsum += (idx + len(key)) ** ord(c)
            hashsum = hashsum % self.capacity

        return hashsum

    def set(self, key, value):
        """ для метода цепочек используется класс Node - описывающий ноду в связном списке """
        self.size += 1
        index = self.hash(key)
        node = self.container[index]

        if node is None:
            self.container[index] = Node(key, value)
            return

        prev = node

        while node is not None:
            prev = node
            node = node.next

        prev.next = Node(key, value)

    def get(self, key):
        index = self.hash(key)
        node = self.container[index]

        while node is not None and node.key != key:
            node = node.next

        if node is None:
            return None

        return node.value

    def delete(self, key):
        index = self.hash(key)
        node = self.container[index]
        prev = None

        while node is not None and node.key != key:
            prev = node
            node = node.next

        if node is None:
            return None

        self.size -= 1
        result = node.value

        if prev is None:
            self.container[index] = node.next
        else:
            prev.next = prev.next.next

        return 
