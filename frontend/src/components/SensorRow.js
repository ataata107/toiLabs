import React, { useEffect, useState } from "react";
import "../index.css";

const SensorRow = React.memo(({ data }) => {
  const [flip, setFlip] = useState(false);

  useEffect(() => {
    setFlip(true);
    const timeout = setTimeout(() => setFlip(false), 600);
    return () => clearTimeout(timeout);
  }, [data.timestamp]);

  const cellClass = flip ? "flip" : "";

  return (
    <tr>
      <td className={cellClass}>{data.deviceId}</td>
      <td className={cellClass}>{data.hydrationLevel}</td>
      <td className={cellClass}>{data.bowelFrequency}</td>
      <td className={cellClass}>
        {new Date(data.timestamp * 1000).toLocaleString()}
      </td>
      <td className={cellClass}>{data.prediction}</td>
    </tr>
  );
});

export default SensorRow;
