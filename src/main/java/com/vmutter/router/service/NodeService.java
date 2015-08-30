package com.vmutter.router.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmutter.router.dao.NodeDAO;
import com.vmutter.router.model.Node;

@Service
public class NodeService {

    @Autowired
    private NodeDAO nodeDAO;

    public void insert(Node node) {
        nodeDAO.insert(node);
    }

    public List<Node> findAll() {
        return nodeDAO.findAll();
    }
}
