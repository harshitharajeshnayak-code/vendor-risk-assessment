-- ==========================================
-- V2__audit_log.sql
-- Create audit log table
-- Java Developer 2 Task
-- ==========================================

CREATE TABLE audit_log (

    -- Primary key
    id BIGSERIAL PRIMARY KEY,

    -- Example: Vendor
    entity_type VARCHAR(100) NOT NULL,

    -- Example: vendor id = 5
    entity_id BIGINT NOT NULL,

    -- CREATE / UPDATE / DELETE
    action_type VARCHAR(50) NOT NULL,

    -- Who performed action
    changed_by VARCHAR(255),

    -- Before update data
    old_value TEXT,

    -- After update data
    new_value TEXT,

    -- IP address optional
    ip_address VARCHAR(50),

    -- Time of action
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ==========================================
-- Indexes for faster lookups
-- ==========================================

-- Main composite index
CREATE INDEX idx_entity_lookup
ON audit_log(entity_type, entity_id);

-- Filter by action
CREATE INDEX idx_action_type
ON audit_log(action_type);

-- Sort/filter by time
CREATE INDEX idx_audit_created_at
ON audit_log(created_at);

-- Search by user
CREATE INDEX idx_changed_by
ON audit_log(changed_by);