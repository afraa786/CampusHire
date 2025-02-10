import React, { useEffect } from 'react';
import Spline from '@splinetool/react-spline';

const apiUrl = 'http://localhost:8080/api/placements';

async function getAllPlacements() {
    const response = await fetch(apiUrl);
    const placements = await response.json();
    const placementsList = document.getElementById('placementsList');
    placementsList.innerHTML = '';

    placements.forEach(placement => {
        const li = document.createElement('li');
        li.textContent = `Company: ${placement.companyName}, Min Percentage: ${placement.minPercentage}, Skills: ${placement.skills}`;
        placementsList.appendChild(li);
    });
}

// Create Placement
document.getElementById('createPlacementForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const companyName = document.getElementById('companyName').value;
    const minPercentage = document.getElementById('minPercentage').value;
    const skills = document.getElementById('skills').value;

    const newPlacement = {
        companyName,
        minPercentage,
        skills
    };

    const response = await fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newPlacement)
    });

    if (response.ok) {
        alert('Placement Created!');
        getAllPlacements();
    } else {
        alert('Error creating placement.');
    }
});

// Update Placement
document.getElementById('updatePlacementForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const id = document.getElementById('updateId').value;
    const companyName = document.getElementById('updateCompanyName').value;
    const minPercentage = document.getElementById('updateMinPercentage').value;
    const skills = document.getElementById('updateSkills').value;

    const updatedPlacement = {
        companyName,
        minPercentage,
        skills
    };

    const response = await fetch(`${apiUrl}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedPlacement)
    });

    if (response.ok) {
        alert('Placement Updated!');
        getAllPlacements();
    } else {
        alert('Error updating placement.');
    }
});

// Delete Placement
document.getElementById('deletePlacementForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const id = document.getElementById('deleteId').value;

    const response = await fetch(`${apiUrl}/${id}`, {
        method: 'DELETE'
    });

    if (response.ok) {
        alert('Placement Deleted!');
        getAllPlacements();
    } else {
        alert('Error deleting placement.');
    }
});

// Initial load
getAllPlacements();

export default function App() {
  return (
    <div>
      {/* Spline Scene */}
      <Spline scene="https://prod.spline.design/hYp29RkxR6I67lhz/scene.splinecode" />

      <div className="container">
        <h1>Placement Criteria Management</h1>

        {/* Create Placement Form */}
        <h2>Create Placement</h2>
        <form id="createPlacementForm">
            <label for="companyName">Company Name:</label>
            <input type="text" id="companyName" required />
            <label for="minPercentage">Minimum Percentage:</label>
            <input type="number" id="minPercentage" required />
            <label for="skills">Required Skills:</label>
            <input type="text" id="skills" required />
            <button type="submit">Create Placement</button>
        </form>

        <hr />

        {/* Display All Placements */}
        <h2>All Placements</h2>
        <ul id="placementsList"></ul>

        <hr />

        {/* Update Placement */}
        <h2>Update Placement</h2>
        <form id="updatePlacementForm">
            <label for="updateId">Placement ID:</label>
            <input type="number" id="updateId" required />
            <label for="updateCompanyName">Company Name:</label>
            <input type="text" id="updateCompanyName" required />
            <label for="updateMinPercentage">Minimum Percentage:</label>
            <input type="number" id="updateMinPercentage" required />
            <label for="updateSkills">Required Skills:</label>
            <input type="text" id="updateSkills" required />
            <button type="submit">Update Placement</button>
        </form>

        <hr />

        {/* Delete Placement */}
        <h2>Delete Placement</h2>
        <form id="deletePlacementForm">
            <label for="deleteId">Placement ID:</label>
            <input type="number" id="deleteId" required />
            <button type="submit">Delete Placement</button>
        </form>
      </div>
    </div>
  );
}
