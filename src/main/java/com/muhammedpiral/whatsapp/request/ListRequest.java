package com.muhammedpiral.whatsapp.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.muhammedpiral.whatsapp.model.SearchCriteria;

import lombok.Data;

@Data
public class ListRequest {

	private List<SearchCriteria> search = new ArrayList<SearchCriteria>();

	private Pageable sortedByName = PageRequest.of(0, 3, Sort.by("name"));

	private Pageable sortedByNameDesc = PageRequest.of(0, 3, Sort.by("name").descending());

	private Pageable sortedByUsernameDescNameAsc = PageRequest.of(0, 5,
			Sort.by("username").descending().and(Sort.by("name")));

}