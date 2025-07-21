// components/SensorTable.js
import React from "react";
import SensorRow from "./SensorRow";

function SensorTable({ data = [] }) {
  return (
    <table>
      <thead>
        <tr>
          <th>Device ID</th>
          <th>Hydration Level</th>
          <th>Bowel Freq.</th>
          <th>Timestamp</th>
          <th>Prediction</th>
        </tr>
      </thead>
      <tbody>
        {data.map((row) => (
          <SensorRow key={row.deviceId} data={row} />
        ))}
      </tbody>
    </table>
  );
}

export default React.memo(SensorTable);
